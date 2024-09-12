---
import { huntEntries } from "../../huntcontent";
// Functions and Data for interacting with csv evidence files
import {
    formatDataitems,
    huntFiles,
    csvToHtmlTable,
} from "../../huntdata/huntdata";
// Layout
import Layout from "../../layouts/Layout.astro";
// Components
import EntryMetadata from "../../components/EntryMetadata.astro";
import Card from "../../components/Card.astro";
// Icons
import Database from "../../components/icons/database.astro";
import Paragraph from "../../components/icons/paragraph.astro"
import { techniques,tactics } from "../../mitre";
// Static path mapping
export async function getStaticPaths() {
    return huntEntries.map((entry) => ({
        params: { slug: entry.id.toLowerCase() },
        props: { entry },
    }));
}
// Constants
const baseURL = import.meta.env.BASE_URL;

const { entry } = Astro.props;

// Conditional styles
const stateStyle = {
    Complete : "uppercase text-white font-bold px-2 py-1 rounded-md bg-lseg-green",
    Partial : "uppercase text-white font-bold px-2 py-1 rounded-md bg-lseg-yellow",
    Incomplete : "uppercase text-white font-bold px-2 py-1 rounded-md bg-lseg-orange"
}

function styleCompleteness(state: keyof typeof stateStyle): string {
    return stateStyle[state] ?  stateStyle[state] : ""
}

const fpStyle = {
    "High" : "text-red-500",
    "Medium" : "text-lseg-orange",
    "Low" : "text-lseg-green"
}
/**Style False Positives with Tailwind styles */
function styleFP(rate : keyof typeof fpStyle): string {
return fpStyle[rate] ?  fpStyle[rate] : ""
}
---

<Layout title={entry.data.title}>
    <main class="max-w-screen-xl mx-10 mt-10 2xl:mx-auto">
        <h1 class="my-5 text-2xl font-lseg">
            {entry.data.title}
        </h1>
        <EntryMetadata entry={entry} />
        <section class="flex gap-4 flex-wrap">
            <div class="border border-slate-100 rounded-sm p-5 grow">
                <h3 class="text-xl text-lseg-darkgrey font-bold mb-5 basis-1/2">Analytics</h3>
                <ul class="text-lseg-darkgrey list-outside ml-5 list-disc">
                    {
                        entry.data.analytics.map((item) => (
                            <li class="my-2">{item.name}</li>
                        ))
                    }
                </ul>
            </div>
            <div class="border border-slate-100 rounded-sm p-5 grow basis-1/2">
                <h3 class="text-xl text-lseg-darkgrey font-bold mb-5">Hypothesis</h3>
                {entry.data.hypothesis.split(/(\n|\r)+/).map(p =><p class="my-2 text-lseg-darkgrey">{p}</p>)}
            </div>
        </section>
        <p class="my-5 text-sm text-black font-semibold">Hunt Completeness :           {
            (entry.data.Hunt_Completeness && entry.data.Hunt_Completeness.length > 0) ? <span class:list={["mx-2", styleCompleteness(entry.data.Hunt_Completeness[0].State)]}>{entry.data.Hunt_Completeness[0].State}</span> <span>{entry.data.Hunt_Completeness[0].Reasoning}</span> : "N/A"
        } </p>
        {entry.data.description.split(/(\n|\r)+/).map(p =><p class="my-2 text-lseg-darkgrey">{p}</p>)}
        <hr class="mt-10 mb-2"/>
        <h3 class="text-xl text-lseg-darkgrey font-bold">MITRE ATT&CK</h3>
        <p class="my-5 text-sm text-black font-semibold">Permissions required: {entry.data.permissions_required.join(', ')}</p>

        <div class="flex flex-wrap justify-start my-10">
        {
            // Techniques
            entry.data.attack_coverage.map((itm) => (
                <Card shade="red-700" title={itm.technique + ": " + techniques.find(tq => tq.id === itm.technique)!.name}>
                    <p class="my-3 text-lseg-darkgrey">Coverage: {itm.coverage}</p>
                    <p class="my-3 text-lseg-darkgrey">Tactics: {itm.tactics.map(t => tactics.find(ta => ta.id === t)!.name).join(', ')}</p>

                    {itm.subtechniques && (<p>Subtechniques</p>
                <ul class="ml-5 list-outside list-disc text-sm text-lseg-darkgrey">
                    {itm.subtechniques.map(st => <li class="my-2"><a class="text-lseg-blue" href={baseURL + "/technique/" + st}>{st}</a>: {techniques.find(tq => tq.id === st)!.name}</li>)}
                </ul>)}
                    <p class="text-sm mt-5 -mb-2 text-lseg-blue"><a href={baseURL + "/technique/" + itm.technique}>
                        View other hunts covering {itm.technique}: {techniques.find(tq => tq.id === itm.technique)!.name}
                    </a></p>
                </Card>

            ))
        }
        </div>
        <hr class="mt-10 mb-2"/>
        <h3 class="text-xl text-lseg-darkgrey font-bold">Analytics</h3>
        {
            entry.data.analytics.map((item) => (

                    <h4 class="mt-10 text-lg text-lseg-medgrey font-bold mb-2"><Paragraph classList="inline -mt-2"/>
                        {item.name}
                    </h4>
                    <p title="Data sources" class="text-lseg-medgrey text-sm">
                        <Database classList="inline fill-sky-400 mr-2 -mt-1" />
                        {item.data_sources.join(", ")}
                    </p>
                    <p class="my-5">False positive rate: <span class={styleFP(item.false_positives)}>{item.false_positives}</span></p>

                    <p class="my-5">{item.description}</p>
                    <div
                        class="max-w-fit"
                        x-data={JSON.stringify({
                            data: item.logic,
                            show: false,
                        })}
                    >
                        <p class="relative mt-5 text-lseg-medgrey font-semibold border rounded-t-md px-5 py-2 text-sm">
                            Logic
                            <>
                                <button
                                    type="button"
                                    class="absolute right-5 text-sm font-mono text-lseg-medgrey"
                                    x-on:click="navigator.clipboard.writeText(data), show = true"
                                >
                                    copy
                                    <span
                                        x-transition
                                        class="absolute py-1 px-4 rounded-md bg-black text-white"
                                        x-show="show"
                                        x-effect="if (show) setTimeout(function() {show = false}, 250)"
                                    >
                                        Text copied
                                    </span>
                                </button>
                            </>
                        </p>
                        <!-- prettier-ignore -->
                        <pre class="bg-slate-700 text-white text-sm p-3 rounded-b-sm overflow-x-scroll">{item.logic}</pre>
                    </div>
            ))
        }
        <hr class="mt-10 mb-2"/>
        <h3 class="my-5 text-xl text-lseg-darkgrey font-bold">Hunter Notes</h3>
        {
            // Turn each paragraph (split by two newlines) into `p` tags
            entry.data.hunter_notes
                .split(/(\n|\r)+/)
                .map((p) => <p class="my-2 text-lseg-darkgrey">{p}</p>)
        }
        <hr class="mt-10 mb-2"/>
        <h3 class="my-5 text-xl text-lseg-darkgrey font-bold">Hunt Outputs</h3>
        {
            // Turn each paragraph (split by two newlines) into `p` tags
            entry.data.hunt_output
                .split(/(\n|\r)+/)
                .map((p) => <p class="my-2 text-lseg-darkgrey">{p}</p>)
        }
        {entry.data.incidents_raised && entry.data.incidents_raised.length > 0 ? <h3 class="my-5 text-xl text-lseg-darkgrey font-semibold">Incidents raised</h3> <ul class="list-disc m-5 text-md text-lseg-darkgrey">{entry.data.incidents_raised.map(inc => <li>{inc}</li>)}</ul> : <p class="text-lseg-green my-5 font-semibold">No incidents were raised for this hunt</p>}

        {entry.data.Detections_Created &&  <h3 class="my-5 text-xl text-lseg-darkgrey font-bold">Detections Created</h3>}

        <div class="grid grid-cols-1 p-5 gap-y-8">
        {
            entry.data.Detections_Created && entry.data.Detections_Created.map(dct => <div><h3 class="font-bold">{dct.Detection.Name}</h3>{dct.Detection.Alert_Triage_Guidance.split(/(\n|\r)+/)
                .map((p) => <p class="my-2 text-lseg-darkgrey">{p}</p>)}</div>)
        }
        </div>
        <h3 class="my-5 text-xl text-lseg-darkgrey font-bold">
            Hunt Results
        </h3>
        {
            // For each evidence file
            entry.data.results_csv?.map((rs, index) =>
                // Try to address this from the map of huntFiles (in /src/huntdata/) and render the table
                // If not exists, render an error to the page
                (rs === null || rs === undefined) ? <p class="my-5 text-yellow-700">Analytic <code>{entry.data.analytics[index].name}</code> had no results.</p> : 
                huntFiles[formatDataitems(rs)] ? (
                    <details class="border border-slate-100 p-5 overflow-auto">
                        <summary class="text-lseg-darkgrey hover:cursor-pointer">
                            {entry.data.analytics[index].name} - <code class="text-sm text-lseg-darkgrey">{rs}</code>. <a class="float-right font-bold text-sm text-lseg-blue" href={"https://gitlab.dx1.lseg.com/app/app-01685/threat-intelligence/threat-hunting/-/raw/main/src/huntdata/" + rs + "?inline=false"}>Download CSV</a>
                        </summary>
                        <Fragment
                            set:html={csvToHtmlTable(
                                huntFiles[formatDataitems(rs)],
                            )}
                        />
                    </details>
                ) : (
                    <p class="my-5 text-red-400">
                        <code class="text-sm">{rs}</code> was entered as result
                        csv for
                        <code class="text-sm">
                            {entry.data.analytics[index].name}
                        </code>
                        but could not be found in 
                        <code class="text-sm">/src/huntdata/</code>
                    </p>
                ),
            )
        }
        <h3 class="my-5 text-xl text-lseg-darkgrey font-bold">
            References
        </h3>
        <ul class="px-10 list-disc">
            {entry.data.references.map(ref => <li><a class="hover:text-lseg-blue" href={ref}>{ref}</a></li>)}
        </ul>
    </main>
</Layout>
