 huntEntries.map((hunt) => (
                        <tr class="text-lseg-darkgrey text-sm">
                            <td class="border-t border-b border-lseg-lightgrey text-center whitespace-nowrap"><a class="py-4 px-4 hover:text-lseg-blue hover:underline" href={hunt.id.toLowerCase()}>{hunt.id}</a></td>
                            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{hunt.data.hunt_ticket}</td>
                            <td class="border-t border-b border-lseg-lightgrey text-left"><a class="block py-4 px-4 hover:text-lseg-blue hover:underline" href={hunt.id.toLowerCase()}>{hunt.data.title}</a></td>
                            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-left"><div class="flex flex-wrap gap-4">{hunt.data.attack_coverage.map(item => [<a title={techniques.find(t => t.id === item.technique)!.name} class="hover:underline hover:text-lseg-blue" href={baseURL + "/technique/" + item.technique}>{item.technique}</a>].concat(item.subtechniques?.map(st => <a class="hover:underline hover:text-lseg-blue" title={techniques.find(t => t.id === st)!.name} href={baseURL + "/technique/" + st}>{st}</a>)))}</div></td>
                            <td class="hidden lg:table-cell border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{hunt.data.platform}</td>
                            <td class="border-t border-b border-lseg-lightgrey py-4 px-4 text-center">{hunt.data.creation_date}</td>
                        </tr>
                    ))
                }
